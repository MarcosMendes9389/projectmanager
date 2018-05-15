package br.com.projectmanager.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

@Configuration
@EnableBatchProcessing
public class ImportSpringBatchConfiguration {

	
	/**
	 * 
	 * @return a reader
	 */

	@Bean
	public ItemReader<ImportCSV> reader() {
			FlatFileItemReader<ImportCSV> reader = new FlatFileItemReader<ImportCSV>();
			reader.setResource(new ClassPathResource("gm-challenge.csv"));
			reader.setLineMapper(new DefaultLineMapper<ImportCSV>() {
				{
					setLineTokenizer(new DelimitedLineTokenizer() {
						{
							setNames(new String[] { "projectname", "plannedstart","plannedend"
								,"pm","pmemail","pmskills", "employeename","employeeemail","employeeteam", "employeeskills" });
						}
					});
					setFieldSetMapper(new BeanWrapperFieldSetMapper<ImportCSV>() {
						{
							setTargetType(ImportCSV.class);
						}
					});
				}
			});
			return reader;
		
	}

	/**
	 * 
	 * @return custom item processor -> anything
	 */
	@Bean
	public ItemProcessor<ImportCSV, ImportCSV> processor() {
		return new ImportItemProcessor();
	}


	/**
	 * 
	 * @param dataSource
	 * @return dummy item writer custom
	 */
	@Bean
	public ItemWriter<ImportCSV> writer(DataSource dataSource) {
			JdbcBatchItemWriter<ImportCSV> writer = new JdbcBatchItemWriter<ImportCSV>();

			
			
			writer.setSql("INSERT INTO importcsv (projectname, plannedstart,plannedend, "
					+ " pm,pmemail,pmskills, employeename,employeeemail,employeeteam, employeeskills)"
					+ " VALUES (:projectname, :plannedstart, :plannedend,"
					+ " :pm, :pmemail,:pmskills, :employeename, :employeeemail, :employeeteam, :employeeskills)");
			writer.setDataSource(dataSource);
			writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ImportCSV>());
			return writer;
		
		
		
	}

	/**
	 * 
	 * @param jobs
	 * @param s1
	 *            steps
	 * @return the Job
	 */
	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(s1).end().build();
	}

	/**
	 * the step 1 contains a reader a processor and a writer using a chunk of 10
	 * 
	 * @param stepBuilderFactory
	 * @param reader
	 * @param writer
	 * @param processor
	 * @return
	 */
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<ImportCSV> reader, ItemWriter<ImportCSV> writer,
			ItemProcessor<ImportCSV, ImportCSV> processor) {
		return stepBuilderFactory.get("step1")
				.<ImportCSV, ImportCSV> chunk(10).reader(reader)
				.processor(processor).writer(writer).build();
	}


	/**
	 * 
	 * @param dataSource
	 * @return JdbcTemplate
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}


	/**
	 * jobLauncherTestUtils utility class for testing batches
	 * 
	 * @return JobLauncherTestUtils
	 */
	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}

}