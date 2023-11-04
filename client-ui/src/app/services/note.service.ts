import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Note} from "../models/note.model";
import {NewNote} from "../models/new-note.model";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http: HttpClient) {
  }

  addNote(formValue: {
    patId: number,
    notes: string
  }) {
    const newNote: NewNote = {
      ...formValue
    };
    this.http.post<NewNote>('http://localhost:8082/patHistory/add', newNote).subscribe();
  }

  getNotePatient(patientId: number): Observable<Note[]> {
    return this.http.get<Note[]>(`http://localhost:8082/patHistory/${patientId}`);
  }

  getNoteId(noteId: string): Observable<Note> {
    return this.http.get<Note>(`http://localhost:8082/patHistory/note/${noteId}`);
  }

  updateNote(formValue: {
    id: string,
    patId: number,
    notes: string
  }) {
    const updatedNote: Note = {
      ...formValue
    }
    this.http.put('http://localhost:8082/patHistory', updatedNote);
  }
}


