package com.tc.nb.service;

import java.io.IOException;

import com.tc.nb.entity.Note;

public interface NotesCreateService {
	void add(Note n) throws IOException;

}
