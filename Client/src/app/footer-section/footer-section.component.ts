import {Component, OnInit} from '@angular/core';
import * as ExternalLinksConstants from 'src/app/models/external-links'
import {ExternalLink} from "src/app/models/external-links";
@Component({
  selector: 'app-footer-section',
  templateUrl: './footer-section.component.html',
  styleUrls: ['./footer-section.component.css']
})
export class FooterSectionComponent implements OnInit{

  externalLinksList: ExternalLink[] = [];

  readonly MAIL_US_TEXT = '';
  ngOnInit() {
    this.externalLinksList = ExternalLinksConstants.EXTERNAL_lINKS;
  }
}
