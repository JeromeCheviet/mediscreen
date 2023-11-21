import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Assess} from "../models/assess.model";
import {AssessService} from "../services/assess.service";

@Component({
  selector: 'app-patient-assess',
  templateUrl: './patient-assess.component.html',
  styleUrls: ['./patient-assess.component.scss']
})
export class PatientAssessComponent implements OnInit {
  @Input() patientId!: string;
  patId!: number;
  patAssess$!: Observable<Assess>;

  constructor(private assessService: AssessService) {
  }

  ngOnInit(): void {
    this.patId = +this.patientId;
    this.patAssess$ = this.assessService.getAssessmentPatient(this.patId);
  }

}
