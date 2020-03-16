package gui;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import strutturaDati.Entry;

public class MastroModel implements TableModel {
	ArrayList<Entry> entryList;
	Entry entry;
	Field[] fields;
	
	public MastroModel(){
		super();
		try{
			this.fields = Class.forName("strutturaDati.Entry").getDeclaredFields();
			entry=new Entry();
			ArrayList<Entry> entryList=new ArrayList<Entry>();
			entryList.add(entry);
			this.entryList=entryList;
		}catch(Exception e){};
		
	}

	public void setEntryList(ArrayList<Entry> entryList){
		this.entryList=entryList;
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return fields[arg0].getType();
	}

	@Override
	public int getColumnCount() {
		return fields.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return fields[arg0].getName();
	}

	@Override
	public int getRowCount() {
		return entryList.size();
	}


	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		fields[arg2].setAccessible(true);
		try {
			fields[arg2].set(entry, (String)arg0);
		} catch (Exception e){}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Field f=fields[columnIndex];
		f.setAccessible(true);
		Object o=null;
		try{o=f.get(entryList.get(rowIndex));}catch(Exception e){}
		return o;
		
		
	}
	
	public void clear(){
		entry=new Entry();
		entryList.clear();
		entryList.add(entry);
	}
	
	

}
