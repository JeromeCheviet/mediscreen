import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PatientService} from "../services/patient.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Patient} from "../models/patient.model";

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.scss']
})
export class UpdatePatientComponent {
  updatePatientForm!: FormGroup;
  patient!: Patient;
  closeIcon: string = "/assets/images/close.png";

  constructor(private formBuilder: FormBuilder,
              private patientService: PatientService,
              private router: Router,
              private route: ActivatedRoute) {
  }

 /* ngOnInit() {
    const patientId = +this.route.snapshot.params['id'];
    this.patient = this.patientService.getPatientById(patientId);
    this.updatePatientForm = this.formBuilder.group({
      patientId: [this.patient.patientId],
      family: [this.patient.family, [Validators.required]],
      given: [this.patient.given, [Validators.required]],
      dob: [this.patient.dob, [Validators.required]],
      sex: [this.patient.sex, [Validators.required]],
      address: [this.patient.address],
      phone: [this.patient.phone]
    });
  }

  onCloseIcon(): void {
    this.router.navigateByUrl(`patient/${this.patient.patientId}`);
  }

  onSubmitForm() {
    console.log(this.updatePatientForm.value);
    this.patientService.updatePatient(this.updatePatientForm.value);
    this.onCloseIcon();
  }*/
}
