import {Component, OnInit} from '@angular/core';
import {PatientService} from "../services/patient.service";
import {Patient} from "../models/patient.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{
  patients!: Patient[];
  patientsColumn: string[] = ["family", "given", "dob", "sex", "address", "phone"];
  constructor(private patientService: PatientService) {
  }

  ngOnInit() {
    this.patients = this.patientService.getAllPatients();
  }

}
