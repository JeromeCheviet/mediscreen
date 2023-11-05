import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NoteService} from "../services/note.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Note} from "../models/note.model";
import {PatientService} from "../services/patient.service";

@Component({
  selector: 'app-update-note',
  templateUrl: './update-note.component.html',
  styleUrls: ['./update-note.component.scss']
})
export class UpdateNoteComponent implements OnInit {
  updateNoteForm!: FormGroup;
  note!: Note;
  closeIcon: string = "/assets/images/close.png"

  constructor(private formBuilder: FormBuilder,
              private noteService: NoteService,
              private patientService: PatientService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  noteId: string = this.route.snapshot.params['id']

  ngOnInit() {
    this.noteService.getNoteId(this.noteId).subscribe((note: Note) => this.updateNoteForm.setValue( {
      id: note.id,
      patId: note.patId,
      notes: note.notes
    }));

    this.updateNoteForm = this.formBuilder.group({
      id: [''],
      patId: [''],
      notes: ['', [Validators.required]]
    });
  }

  onCloseIcon(): void {
    this.router.navigateByUrl(`patient/${this.updateNoteForm.value.patId}`);
  }

  onSubmitForm(): void {
    console.log(this.updateNoteForm.value);
    this.noteService.updateNote(this.updateNoteForm.value);
    this.patientService.getPatientById(this.updateNoteForm.value.patId)
    this.onCloseIcon();
  }

}
