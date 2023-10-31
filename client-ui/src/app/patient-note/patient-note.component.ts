import {Component, Input, OnInit} from '@angular/core';
import {Patient} from "../models/patient.model";

@Component({
  selector: 'app-patient-note',
  templateUrl: './patient-note.component.html',
  styleUrls: ['./patient-note.component.scss']
})
export class PatientNoteComponent {
  @Input() patientId!: String;
  patId!: number

  ngOnInit(): void {
    this.patId = +this.patientId
  }
}
