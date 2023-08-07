import {Component, OnInit} from '@angular/core';
import {Patient} from "../models/patient.model";
import {PatientService} from "../services/patient.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-patient-file',
  templateUrl: './patient-file.component.html',
  styleUrls: ['./patient-file.component.scss']
})
export class PatientFileComponent implements OnInit {
  patient!: Patient;
  sexIcon!: string;
  editIcon: string = "/assets/images/write.png";
  closeIcon: string = "/assets/images/close.png";


  constructor(private patientService: PatientService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    const patientId = +this.route.snapshot.params['id'];
    this.patient = this.patientService.getPatientById(patientId);
    if (this.patient.sex === "M") {
      this.sexIcon = "/assets/images/male.png";
    } else {
      this.sexIcon = "/assets/images/female.png";
    }
  }

  onUpdatePatient(): void {
    this.router.navigateByUrl(`updatePatient/${this.patient.patientId}`)
  }

}
