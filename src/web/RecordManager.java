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
        	throw new ValidationException("jmn�no neexistuje");
        }
        String[] nameAndSurname = record.getNameSurnameStr().split(" ");
        if (nameAndSurname.length < 2) {
        	throw new ValidationException("Jm�no a P�ijmen� mus� b�t alespo� dv� slova");
        }
        String surname = nameAndSurname[nameAndSurname.length-1];
        String name = nameAndSurname[0];
        for (int i = 1; i < nameAndSurname.length-1; i++) {
    		name += " " + nameAndSurname[i];
    	}
        record.setName(name);
        record.setSurname(surname);
        
        LocalDateTime ldt = LocalDateTime.now();
        LocalDate born = null;
        try {
        	born = LocalDate.parse(record.getDateStr());
	        if (born.isAfter(ldt.toLocalDate())) {
	            throw new ValidationException("Narozen� je v budoucnosti");
	        }
        } catch (DateTimeParseException ex) {
        	throw new ValidationException("�patn� form�t datumu narozen�");
        }
        record.setDate(born);
        
        if (!isValidAddress(record.getEmail())) {
            throw new ValidationException("�patn� form�t emailov� adresy");
        }

    }

    private boolean isValidAddress(String address) {
        return address.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]+");
    }

}
