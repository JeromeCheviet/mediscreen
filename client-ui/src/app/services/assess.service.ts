import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assess} from "../models/assess.model";

@Injectable({
  providedIn: 'root'
})
export class AssessService {

  constructor(private http: HttpClient) {
  }

  getAssessmentPatient(patientId: number): Observable<Assess> {
    return this.http.get<Assess>(`http://localhost:8083/assess/${patientId}`)
  }

}
