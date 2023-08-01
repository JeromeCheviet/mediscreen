import {Component, Input, OnInit} from '@angular/core';
import {Patient} from "../models/patient.model";
import {PatientService} from "../services/patient.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-patient-file',
  templateUrl: './patient-file.component.html',
  styleUrls: ['./patient-file.component.scss']
})
export class PatientFileComponent implements OnInit {
  patient!: Patient;
  imgMale:any = "assets/images/male-icon.png"

  constructor(private patientService: PatientService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const patientId = +this.route.snapshot.params['id'];
    this.patient = this.patientService.getPatientById(patientId);
  }

}
