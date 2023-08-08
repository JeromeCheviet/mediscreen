import {Component, OnInit} from '@angular/core';
import {Patient} from "../models/patient.model";
import {PatientService} from "../services/patient.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-patient-file',
  templateUrl: './patient-file.component.html',
  styleUrls: ['./patient-file.component.scss']
})
export class PatientFileComponent implements OnInit {
  //patient!: Patient;
  patient$!: Observable<Patient>;
  sexMIcon: String = "/assets/images/male.png";
  sexFIcon: String = "/assets/images/female.png";
  editIcon: string = "/assets/images/write.png";
  closeIcon: string = "/assets/images/close.png";
  patientId!: number;


  constructor(private patientService: PatientService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.patientId = +this.route.snapshot.params['id'];
    this.patient$ = this.patientService.getPatientById(this.patientId);
  }

  onUpdatePatient(): void {
    this.router.navigateByUrl(`updatePatient/${this.patientId}`);
  }

}
