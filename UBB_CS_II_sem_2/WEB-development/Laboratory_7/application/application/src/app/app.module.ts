import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DocumentsComponent } from './documents/documents.component';
import { DocListComponent } from './documents/doc-list/doc-list.component';
import {MatListModule} from "@angular/material/list";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatToolbarModule} from "@angular/material/toolbar";
import { AddDocsComponent } from './documents/modify-docs/add-docs.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatTabsModule} from "@angular/material/tabs";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { DeleteDocComponent } from './documents/delete-doc/delete-doc.component';

@NgModule({
  declarations: [
    AppComponent,
    DocumentsComponent,
    DocListComponent,
    AddDocsComponent,
    DeleteDocComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatListModule,
        FormsModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatInputModule,
        MatTabsModule,
        MatIconModule,
        ReactiveFormsModule,
        MatButtonModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
