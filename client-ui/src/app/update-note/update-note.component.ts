import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {NoteService} from "../services/note.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-note',
  templateUrl: './update-note.component.html',
  styleUrls: ['./update-note.component.scss']
})
export class UpdateNoteComponent implements OnInit {
  updateNoteForm!: FormGroup;
  note!: Node;
  closeIcon: string = "/assets/images/close.png"

  constructor(private formBuilder: FormBuilder,
              private noteService: NoteService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  noteId: string = this.route.snapshot.params['id']

  ngOnInit() {
    console.log(this.noteId)
  }

}
