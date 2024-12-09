//Basic Modules
import { NgModule } from '@angular/core';
import { ModalModule } from 'ngb-modal';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgChartsModule } from 'ng2-charts';
import { TimeagoModule } from 'ngx-timeago';
import { httpInterceptorProviders } from './helpers/http.interceptor';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

//JQX widgets
import { jqxDataTableModule } from 'jqwidgets-ng/jqxdatatable';
import { jqxButtonModule } from 'jqwidgets-ng/jqxbuttons';
import { jqxInputModule } from 'jqwidgets-ng/jqxinput';
import { jqxTabsModule } from 'jqwidgets-ng/jqxtabs';
import { jqxValidatorModule } from 'jqwidgets-ng/jqxvalidator';
import { jqxTextAreaModule } from 'jqwidgets-ng/jqxtextarea';
import { jqxNotificationModule } from 'jqwidgets-ng/jqxnotification';
import { jqxGridModule } from 'jqwidgets-ng/jqxgrid';
import { jqxWindowModule } from 'jqwidgets-ng/jqxwindow';


import { AppComponent } from './app.component';


//Layouts
import { HeaderComponent } from './layouts/header/header.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { FooterComponent } from './layouts/footer/footer.component';



//pages
import { UsersProfileComponent } from './pages/users-profile/users-profile.component';
import { PagesLoginComponent } from './pages/pages-login/pages-login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

//maker functionalities
import { AddStaffComponent } from './components/maker/staff/add-staff/add-staff.component';
import { ManageStaffComponent } from './components/maker/staff/manage-staff/manage-staff.component';

import { AddInstitutionComponent } from './components/maker/institution/add-institution/add-institution.component';
import { ManageInstitutionComponent } from './components/maker/institution/manage-institution/manage-institution.component';

import { ManageAccountComponent } from './components/maker/account/manage-account/manage-account.component';

import { CurrencyComponent } from './components/maker/currency/currency/currency.component';
import { ManageCurrencyComponent } from './components/maker/currency/manage-currency/manage-currency.component';

import { ManageLoanComponent } from './components/maker/loan/manage-loan/manage-loan.component';
import { RequestLoanComponent } from './components/maker/loan/request-loan/request-loan.component';


//admin functionalities
import { AddRoleComponent } from './components/admin/role/add-role/add-role.component';
import { ManageRoleComponent } from './components/admin/role/manage-role/manage-role.component';
import { RoleDetailsComponent } from './components/admin/role/role-details/role-details.component';

import { AddRightComponent } from './components/admin/right/add-right/add-right.component';
import { ManageRightComponent } from './components/admin/right/manage-right/manage-right.component';
import { RightDetailsComponent } from './components/admin/right/right-details/right-details.component';

import { AddUserComponent } from './components/admin/user/add-user/add-user.component';
import { ManageUsersComponent } from './components/admin/user/manage-users/manage-users.component';

import { ManageBranchesComponent } from './components/admin/branch/manage-branches/manage-branches.component';
import { CreateBranchComponent } from './components/admin/branch/create-branch/create-branch.component';
import { BranchesComponent } from './components/admin/reports/branches/branches.component';


import { AddRegionComponent } from './components/admin/region/add-region/add-region.component';
import { ManageRegionComponent } from './components/admin/region/manage-region/manage-region.component';

import { BackupComponent } from './components/admin/backup/backup.component';
import { RestoreComponent } from './components/admin/restore/restore.component';

//Approver Functionalities
import { ManageLoansApproverComponent } from './components/approver/manage-loans-approver/manage-loans-approver.component';

//reports
import { ApprovedLoansMakerComponent } from './components/maker/reports/approved-loans-maker/approved-loans-maker.component';
import { ApprovedButNotDisbursedLoansMakerComponent } from './components/maker/reports/approved-but-not-disbursed-loans-maker/approved-but-not-disbursed-loans-maker.component';
import { UserReportComponent } from './components/admin/reports/user-report/user-report.component';
import { ForgetPasswordComponent } from './pages/forget-password/forget-password.component';
import { UpdatePasswordComponent } from './pages/update-password/update-password.component';
import { OnlineFailedUsersComponent } from './components/admin/reports/online-failed-users/online-failed-users.component';
import { ManageUserStatusComponent } from './components/admin/user/manage-user-status/manage-user-status.component';
import { ApprovedButNotDisbursedLoanHoComponent } from './components/ho/reports/approved-but-not-disbursed-loan-ho/approved-but-not-disbursed-loan-ho.component';
import { SummaryReportHoComponent } from './components/ho/reports/summary-report-ho/summary-report-ho.component';
import { RejectedLoansApproverComponent } from './components/approver/reports/rejected-loans-approver/rejected-loans-approver.component';
import { InvalidTokenComponent } from './pages/invalid-token/invalid-token.component';
import { DataTablesModule } from 'angular-datatables';

import { PlatformModule } from '@angular/cdk/platform';
import { LoanStatusComponent } from './components/ho/loan-status/loan-status/loan-status.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { BrowserAnimationsModule}from '@angular/platform-browser/animations';

import { DisbursedLoansReportHoComponent } from './components/ho/reports/disbursed-loans-report-ho/disbursed-loans-report-ho.component';
import { ApprovedNotDisbursedComponent } from './components/approver/reports/approved-not-disbursed/approved-not-disbursed.component';
import { ApprovedButNotDisbursedComponent } from './components/approver/reports/approved-but-not-disbursed/approved-but-not-disbursed.component';
import { PendingLoansForApproverComponent } from './components/approver/reports/pending-loans-for-approver/pending-loans-for-approver.component';
import { RegionalLoansReportComponent } from './components/ho/reports/regional-loans-report/regional-loans-report.component';
import { DarkModeToggleComponent } from './pages/dark-mode-toggle/dark-mode-toggle.component';
import { AddContactComponent } from './components/admin/contacts/add-contact/add-contact.component';
import { NeedHelpComponent } from './contacts/need-help/need-help.component';
import { ApprovedLoansComponent } from './components/maker/loan/approved-loans/approved-loans.component';
import { RejectedLoansComponent } from './components/approver/rejected-loans/rejected-loans.component';
import { DisbursedLoansComponent } from './components/maker/reports/disbursed-loans/disbursed-loans.component';
import { DisbursedLoansApproverComponent } from './components/approver/reports/disbursed-loans-approver/disbursed-loans-approver.component';
import { DisbursedLoansOtherRegionsComponent } from './components/approver/reports/disbursed-loans-other-regions/disbursed-loans-other-regions.component';
import { UserActivitiesComponent } from './components/admin/user-activities/user-activities.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AddCpoComponent } from './components/cpo/add-cpo/add-cpo.component';
import { CpoListComponent } from './components/cpo/cpo-list/cpo-list.component';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatSelectModule } from '@angular/material/select';
import { AddCompanyComponent } from './components/company/add-company/add-company.component';
import { CompanyListComponent } from './components/company/company-list/company-list.component';
import { AddAuctionComponent } from './components/Auction/add-auction/add-auction.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { AuctionListComponent } from './components/Auction/auction-list/auction-list.component';
import { AddSampleComponent } from './components/Sample/add-sample/add-sample.component';
import { SampleListComponent } from './components/Sample/sample-list/sample-list.component';
import { ConfirmationDialogComponent } from './components/confirmation/confirmation-dialog/confirmation-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    DashboardComponent,
    UsersProfileComponent,
    PagesLoginComponent,
    AddStaffComponent,
    AddInstitutionComponent,
    ManageInstitutionComponent,
    ManageStaffComponent,
    ManageAccountComponent,
    ManageCurrencyComponent,
    AddRoleComponent,
    ManageRoleComponent,
    RoleDetailsComponent,
    AddRightComponent,
    ManageRightComponent,
    RightDetailsComponent,
    AddUserComponent,
    ManageUsersComponent,
    ManageBranchesComponent,
    CreateBranchComponent,
    ManageLoanComponent,
    RequestLoanComponent,
    ManageLoansApproverComponent,
    CurrencyComponent,
    ApprovedNotDisbursedComponent,
    ApprovedButNotDisbursedComponent,
    ApprovedLoansMakerComponent,
    ApprovedButNotDisbursedLoansMakerComponent,
    AddRegionComponent,
    ManageRegionComponent,
    BackupComponent,
    RestoreComponent,
    UserReportComponent,
    BranchesComponent,
    NotFoundComponent,
    ForgetPasswordComponent,
    UpdatePasswordComponent,
    OnlineFailedUsersComponent,
    RejectedLoansApproverComponent,
    SummaryReportHoComponent,
    ApprovedButNotDisbursedLoanHoComponent,
    ManageUserStatusComponent,
    InvalidTokenComponent,
    LoanStatusComponent,
    DisbursedLoansReportHoComponent,
    PendingLoansForApproverComponent,
    RegionalLoansReportComponent,
    DarkModeToggleComponent,
    NeedHelpComponent,
    AddContactComponent,
    ApprovedLoansComponent,
    RejectedLoansComponent,
    DisbursedLoansComponent,
    DisbursedLoansApproverComponent,
    DisbursedLoansOtherRegionsComponent,
    UserActivitiesComponent,
    AddCpoComponent,
    CpoListComponent,
    AddCompanyComponent,
    CompanyListComponent,
    AddAuctionComponent,
    AuctionListComponent,
    AddSampleComponent,
    SampleListComponent,
    ConfirmationDialogComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule,
    jqxDataTableModule,
    jqxButtonModule,
    jqxInputModule,
    jqxTabsModule,
    jqxValidatorModule,
    jqxTextAreaModule,
    ModalModule,
    jqxNotificationModule,
    jqxGridModule,
    jqxWindowModule,   // OwlNativeDateTimeModule,
    NgChartsModule,
    TimeagoModule.forRoot(),
    DataTablesModule,
    PlatformModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    NgMultiSelectDropDownModule.forRoot(),
    MatPaginatorModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    MatNativeDateModule,
    MatDatepickerModule, 
    MatInputModule, 
    MatRadioModule


  ],
  providers: [httpInterceptorProviders,MatDatepickerModule],
 
  bootstrap: [AppComponent]
})
export class AppModule { }

