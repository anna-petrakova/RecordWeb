package web;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import common.ValidationException;

public class RecordManager {
	private List<Record> records;
	
	public RecordManager() {
		records = new ArrayList<>();		
	}
	
	public List<Record> getRecords() {
		return records;
	}
	
	public void addRecord(Record r) {
		records.add(r);
	}
	
	public void createRecord(Record record) {
		validate(record);
		records.add(record);
	}
	
	private void validate(Record record) {
        if (record == null) {
            throw new IllegalArgumentException("record is null");
        }
        if (record.getNameSurnameStr() == null) {
        	throw new ValidationException("jmnéno neexistuje");
        }
        String[] nameAndSurname = record.getNameSurnameStr().split(" ");
        if (nameAndSurname.length != 2) {
        	throw new ValidationException("Jméno a Pøijmení musí být dvì slova");
        }
        String surname = nameAndSurname[1];
        String name = nameAndSurname[0];
        
        record.setName(name);
        record.setSurname(surname);
        
        LocalDateTime ldt = LocalDateTime.now();
        LocalDate born = null;
        try {
        	born = LocalDate.parse(record.getDateStr());
	        if (born.isAfter(ldt.toLocalDate())) {
	            throw new ValidationException("Narození je v budoucnosti");
	        }
        } catch (DateTimeParseException ex) {
        	throw new ValidationException("Špatný formát datumu narození");
        }
        record.setDate(born);
    }

}
