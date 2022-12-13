package com.tc.nb.dal.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.tc.nb.dal.NotesDAO;
import com.tc.nb.entity.Note;

public class FileNotesDAO implements NotesDAO {

	private List<Note> notes = new ArrayList<Note>();
	private static final String soursePath = ".\\resourses\\note.txt";

	public FileNotesDAO() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(soursePath));
			String line = null;
			while ((line = reader.readLine()) != null) {
				int index = line.lastIndexOf(' ');
				if (index >= 0) {
			
					String context = line.substring(0, index);
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					String str = line.substring(index);
					Date date = new Date();
					try {
						date = format.parse(str);
					} catch (ParseException e) {    //  в отдельном методе

					}
					notes.add(new Note(context, date));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override public List<Note> load() {
		return notes;
	}

	@Override
	public void save(Note note) throws IOException {
		notes.add(note);
		FileWriter writer = new FileWriter(soursePath);
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			for (Note n : notes) {
				writer.write(n.getContext() + " " + format.format(n.getDate()) + "\n");
			}
		} catch (IOException e) {

		} finally {
			writer.close();
		}
	}

}
