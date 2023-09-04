import {Component, OnInit} from '@angular/core';
import {PatientService} from "../services/patient.service";
import {Patient} from "../models/patient.model";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    patients$!: Observable<Patient[]>;
    patientsColumn: string[] = ["family", "given", "dob", "sex", "address", "phone"];

    constructor(private patientService: PatientService,
                private router: Router) {
    }

    ngOnInit() {
        this.patients$ = this.patientService.getAllPatients();
        console.log(this.patients$)
    }

    onViewPatient(patientRow: Patient) {
        this.router.navigateByUrl(`patient/${patientRow.patientId}`)
    }

}
