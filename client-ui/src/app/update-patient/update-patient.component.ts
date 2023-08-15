import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PatientService} from "../services/patient.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Patient} from "../models/patient.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.scss']
})
export class UpdatePatientComponent {
  updatePatientForm!: FormGroup;
  patient$!: Observable<Patient>;
  //patient!: Patient;
  closeIcon: string = "/assets/images/close.png";

  constructor(private formBuilder: FormBuilder,
              private patientService: PatientService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const patientId = +this.route.snapshot.params['id'];
    const patient: Patient = new Patient()

    this.patientService.getPatientById(patientId).subscribe(value => {
      value.patientId = patient.patientId;
      value.family = patient.family;
      value.given = patient.given;
      value.dob = patient.dob;
      value.sex = patient.sex;
      value.address = patient.address;
      value.phone = patient.phone;
    });
    ;
    console.log(patient.patientId)
    this.updatePatientForm = this.formBuilder.group({
      patientId: [patient.patientId],
      family: [patient.family, [Validators.required]],
      given: [patient.given, [Validators.required]],
      dob: [patient.dob, [Validators.required]],
      sex: [patient.sex, [Validators.required]],
      address: [patient.address],
      phone: [patient.phone]
    });
  }

  onCloseIcon(): void {
    // this.router.navigateByUrl(`patient/${this.patient.patientId}`);
  }

  onSubmitForm() {
    console.log(this.updatePatientForm.value);
    this.patientService.updatePatient(this.updatePatientForm.value);
    this.onCloseIcon();
  }
}
