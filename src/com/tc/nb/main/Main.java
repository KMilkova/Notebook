package com.tc.nb.main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.tc.nb.dal.DAOProvider;
import com.tc.nb.dal.impl.FileNotesDAO;
import com.tc.nb.entity.Note;
import com.tc.nb.service.NotesFindService;
import com.tc.nb.service.impl.NotesCreateServiceImpl;
import com.tc.nb.service.impl.NotesFindServiceImpl;
import com.tc.nb.view.NotesView;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		List<Note> notes = new ArrayList<Note>();
		DAOProvider provider = DAOProvider.getInstance();
		NotesView nView = new NotesView();
		NotesFindServiceImpl findService = new NotesFindServiceImpl();
		NotesCreateServiceImpl createService = new NotesCreateServiceImpl();
		int x;
		System.out.println("");
		boolean cheak = true;
		while (cheak) {
			Scanner in = new Scanner(System.in);
			System.out.print("1 - создать новый блокнот\n" 
					+ "2 - добавить запись в блокнот\n"
					+ "3 - найти запись по содержимому\n" 
					+ "4 - найти запись по дате создания\n"
					+ "5 - показать записи\n" 
					+ "6 - загрузить блокнот в файл\n" 
					+ "7 - загрузить блокнот из файла\n");
			x = in.nextInt();
			switch (x) {
			case 1:
				notes = new ArrayList<Note>();
				break;
			case 2:
				createService.add(new Note("fff sss ss", new Date(2022 - 1900, 11, 13)));
				break;
			case 3:
				nView.printNotes(findService.findByContent("aaaa fff"));
				break;
			case 4:
				Date date = new Date(2022 - 1900, 11, 24);
				nView.printNotes(findService.findByDate(date));
				break;
			case 5:
				nView.printNotes(notes);
				break;
			case 6:
				for(Note n: notes) {
				provider.getNotesDAO().save(n);					
				}
				break;
			case 7:
				notes = provider.getNotesDAO().load();
				break;
			default:
				cheak = false;
			}
		}
		System.out.println("end");

	}

}
