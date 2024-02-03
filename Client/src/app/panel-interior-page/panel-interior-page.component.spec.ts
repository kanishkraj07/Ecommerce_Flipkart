import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelInteriorPageComponent } from './panel-interior-page.component';

describe('PanelInteriorPageComponent', () => {
  let component: PanelInteriorPageComponent;
  let fixture: ComponentFixture<PanelInteriorPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PanelInteriorPageComponent]
    });
    fixture = TestBed.createComponent(PanelInteriorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
