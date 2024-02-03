export interface CustomerAccountDetails {
  customerId: string;
  firstName?: string;
  lastName?: string;
  gender?: string;
  emailId?: string;
  mobileNo: number;
  address?: string;
  pincode?: number;
  registeredTimestamp?: string;
}

export interface CustomerContext {
  customer: CustomerAccountDetails;
}
