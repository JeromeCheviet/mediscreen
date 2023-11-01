import {Component, Input, OnInit} from '@angular/core';
import {Patient} from "../models/patient.model";
import {Observable} from "rxjs";
import {Note} from "../models/note.model";
import {NoteService} from "../services/note.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-patient-note',
  templateUrl: './patient-note.component.html',
  styleUrls: ['./patient-note.component.scss']
})
export class PatientNoteComponent implements OnInit {
  @Input() patientId!: String;
  patId!: number;
  patHist$!: Observable<Note[]>;
  patHistColumn: string[] = ["notes"];

  constructor(private noteService: NoteService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.patId = +this.patientId
    this.patHist$ = this.noteService.getNotePatient(this.patId)
  }

  onAddNote(): void {
    this.router.navigateByUrl(`addNote/${+this.patientId}`)
  }

  onEditNote(patHistRow: Note): void {
    console.log("Edit note")
  }
}
