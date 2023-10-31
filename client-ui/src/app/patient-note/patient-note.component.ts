import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-patient-note',
  templateUrl: './patient-note.component.html',
  styleUrls: ['./patient-note.component.scss']
})
export class PatientNoteComponent {
  @Input() patId!: string;
}
