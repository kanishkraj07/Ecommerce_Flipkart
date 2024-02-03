export interface ExternalLink {
  title: string;
  link: Link[];
}

export interface Link{
  value: string;
  redirectUrl: string;
}

export const EXTERNAL_lINKS: ExternalLink[] = [
  {
    title: 'about',
    link: [{ value: 'Contact Us', redirectUrl: ''},{ value: 'About Us', redirectUrl: ''},{ value: 'Careers', redirectUrl: ''},{ value: 'Flipkart Stories', redirectUrl: ''},
           { value: 'Press', redirectUrl: ''},{ value: 'Flipkart Wholesale', redirectUrl: ''}, { value: 'Cleartrip', redirectUrl: ''}, { value: 'Corporate Information', redirectUrl: ''}]
  },
  {
    title: 'help',
    link: [{ value: 'Payments', redirectUrl: ''},{ value: 'Shipping', redirectUrl: ''},{ value: 'Cancellation & Returns', redirectUrl: ''},{ value: 'FAQ', redirectUrl: ''},
      { value: 'Report Infringement', redirectUrl: ''}]
  },
  {
    title: 'consumer policy',
    link: [{ value: 'Cancellation & Returns', redirectUrl: ''},{ value: 'Terms Of Use', redirectUrl: ''},{ value: 'Security', redirectUrl: ''},{ value: 'Privacy', redirectUrl: ''},
      { value: 'Sitemap', redirectUrl: ''},{ value: 'Grievance Redressal', redirectUrl: ''}, { value: 'EPR Compliance', redirectUrl: ''}]
  },
  {
    title: 'social',
    link: [{ value: 'Facebook', redirectUrl: 'https://www.facebook.com/flipkart'},{ value: 'Twitter', redirectUrl: 'https://twitter.com/flipkart'},{ value: 'Youtube', redirectUrl: 'https://www.youtube.com/flipkart'}]
  }
]
