import {Component, Input, OnInit} from '@angular/core';
import {Patient} from "../models/patient.model";
import {Observable} from "rxjs";
import {Note} from "../models/note.model";
import {NoteService} from "../services/note.service";

@Component({
  selector: 'app-patient-note',
  templateUrl: './patient-note.component.html',
  styleUrls: ['./patient-note.component.scss']
})
export class PatientNoteComponent {
  @Input() patientId!: String;
  patId!: number
  patHist$!: Observable<Note[]>;

  constructor(private noteService: NoteService) {
  }

  ngOnInit(): void {
    this.patId = +this.patientId
    this.patHist$ = this.noteService.getNotePatient(this.patId)
  }
}
