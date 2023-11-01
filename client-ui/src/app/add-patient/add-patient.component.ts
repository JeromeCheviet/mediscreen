import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PatientService} from "../services/patient.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.scss']
})
export class AddPatientComponent implements OnInit {
  addPatientForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private patientService: PatientService,
              private router: Router) {
  }

  ngOnInit() {
    this.addPatientForm = this.formBuilder.group({
      family: [null, [Validators.required]],
      given: [null, [Validators.required]],
      dob: [null, [Validators.required]],
      sex: [null, [Validators.required]],
      address: [null],
      phone: [null]
      });
  }

  onSubmitForm() {
    console.log(this.addPatientForm.value)
    this.patientService.addPatient(this.addPatientForm.value)
    this.router.navigateByUrl('');
  }
}
