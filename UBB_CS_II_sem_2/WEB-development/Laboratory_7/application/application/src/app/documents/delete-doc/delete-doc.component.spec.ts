import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDocComponent } from './delete-doc.component';

describe('DeleteDocComponent', () => {
  let component: DeleteDocComponent;
  let fixture: ComponentFixture<DeleteDocComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteDocComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteDocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
