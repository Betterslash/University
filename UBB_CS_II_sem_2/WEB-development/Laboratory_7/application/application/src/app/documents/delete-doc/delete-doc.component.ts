import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DocumentService} from "../shared/document.service";

@Component({
  selector: 'app-delete-doc',
  templateUrl: './delete-doc.component.html',
  styleUrls: ['./delete-doc.component.css']
})
export class DeleteDocComponent implements OnInit {
  deleteForm: FormGroup;

  constructor(private formBuilder : FormBuilder,
              private service : DocumentService) { }

  ngOnInit(): void {
    this.deleteForm = this.formBuilder.group({
      id : ['', Validators.required]
    })
  }

  onSubmit(value) {
    if(this.deleteForm.valid){
      this.service.delete(value.id).subscribe(data => console.log(data));
      this.deleteForm.reset();
    }else{
      alert('Please give an ID');
    }
  }
}
