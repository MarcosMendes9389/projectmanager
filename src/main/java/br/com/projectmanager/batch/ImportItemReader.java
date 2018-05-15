package br.com.projectmanager.batch;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ImportItemReader implements ItemReader<ImportCSV> {

	private List<ImportCSV> importrows;

	private Iterator<ImportCSV> iterator;

	
	
	public ImportCSV read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		if (getIterator().hasNext()) {
			return getIterator().next();
		}
		return null;

	}

	public List<ImportCSV> getImportRows() {
		return importrows;
	}

	public void setPojos(List<ImportCSV> importrows) {
		this.importrows = importrows;
	}

	public Iterator<ImportCSV> getIterator() {
		return iterator;
	}

	public void setIterator(Iterator<ImportCSV> iterator) {
		this.iterator = iterator;
	}

}
