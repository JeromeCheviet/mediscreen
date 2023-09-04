import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PatientService} from "../services/patient.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Patient} from "../models/patient.model";
import {map, Observable} from "rxjs";

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

  patientId = +this.route.snapshot.params['id'];

  ngOnInit() {

    this.patientService.getPatientById(this.patientId).subscribe((patient) => this.updatePatientForm.setValue({
      patientId: patient.patientId,
      family: patient.family,
      given: patient.given,
      dob: patient.dob,
      sex: patient.sex,
      address: patient.address,
      phone: patient.phone
    }))

    this.updatePatientForm = this.formBuilder.group({
      patientId: [''],
      family: ['', [Validators.required]],
      given: ['', [Validators.required]],
      dob: ['', [Validators.required]],
      sex: ['', [Validators.required]],
      address: [''],
      phone: ['']
    });
  }

  onCloseIcon(): void {
    this.router.navigateByUrl(`patient/${this.patientId}`);
  }

  onSubmitForm() {
    console.log(this.updatePatientForm.value);
    this.patientService.updatePatient(this.updatePatientForm.value);
    this.patientService.getPatientById(this.patientId)
    this.onCloseIcon();
  }
}
