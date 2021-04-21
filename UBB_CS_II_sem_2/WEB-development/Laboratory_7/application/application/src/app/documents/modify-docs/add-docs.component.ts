import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DocumentService} from "../shared/document.service";

@Component({
  selector: 'app-add-docs',
  templateUrl: './add-docs.component.html',
  styleUrls: ['./add-docs.component.css']
})
export class AddDocsComponent implements OnInit {
  documentForm: FormGroup;

  constructor(private formBuilder : FormBuilder, private docService : DocumentService) { }

  ngOnInit(): void {
    this.documentForm = this.formBuilder.group({
      id : ['', Validators.required],
      title : ['', Validators.required],
      pageNumber : ['', Validators.required],
      format : ['', Validators.required],
      type : ['', Validators.required],
      authorId : ['', Validators.required]
    })
  }

  onSubmit(value) {
    if(this.documentForm.valid) {
      this.docService.save(value).subscribe(data => console.log(data));
      this.documentForm.reset();
    }else{
      alert('Make sure to complete every field!');
    }
  }
}
