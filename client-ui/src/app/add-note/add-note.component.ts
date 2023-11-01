import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {NoteService} from "../services/note.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.scss']
})
export class AddNoteComponent implements OnInit {
  addNoteForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private noteService: NoteService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.addNoteForm = this.formBuilder.group({

    });
  }

}
