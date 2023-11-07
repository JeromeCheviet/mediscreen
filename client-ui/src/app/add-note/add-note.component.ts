import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NoteService} from "../services/note.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.scss']
})
export class AddNoteComponent implements OnInit {
  addNoteForm!: FormGroup;
  closeIcon: string = "/assets/images/close.png";

  constructor(private formBuilder: FormBuilder,
              private noteService: NoteService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  patId: number = +this.route.snapshot.params['id'];

  ngOnInit(): void {
    this.addNoteForm = this.formBuilder.group({
      patId: [this.patId],
      notes: [null, [Validators.required]]
    });
  }

  onCloseIcon(): void {
    this.router.navigateByUrl(`patient/${this.patId}`);
  }

  onSubmitForm(): void {
    console.log(this.addNoteForm.value);
    this.noteService.addNote(this.addNoteForm.value);
    this.onCloseIcon();
  }

}
