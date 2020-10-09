package com.rav.raverp.network;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.CustomerListModel;
import com.rav.raverp.data.model.api.EditEmailModel;
import com.rav.raverp.data.model.api.EditMobileModel;
import com.rav.raverp.data.model.api.FollowUpListModel;
import com.rav.raverp.data.model.api.FollowUpModel;
import com.rav.raverp.data.model.api.GetBlockModel;
import com.rav.raverp.data.model.api.GetProfileModel;
import com.rav.raverp.data.model.api.GetProjectModel;
import com.rav.raverp.data.model.api.LeadListModel;
import com.rav.raverp.data.model.api.LoginModel;
import com.rav.raverp.data.model.api.MyGoalListModel;
import com.rav.raverp.data.model.api.PlotAvailableModel;
import com.rav.raverp.data.model.local.ChangePasswordModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiHelper {

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.LOGIN)
    Call<ApiResponse<List<LoginModel>>> userLogin(@Query("LoginId") String LoginId, @Query("Password") String Password);

    @Headers({"Content-Type: application/json"})
    @GET(ApiEndPoint.AvailablePlot)
    Call<ApiResponse<List<PlotAvailableModel>>> getPlotAvailable();
    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.ChangePassword)
    Call<ApiResponse<List<ChangePasswordModel>>> UpdateChangePassword(@Query("LoginId") String LoginId,
                                                                      @Query("OldPassword") String Password,
                                                                      @Query("NewPassword") String NewPassword,
                                                                      @Query("ConfirmPassword")String ConfirmPassword,
                                                                      @Query("RoleId")Integer RoleId);
    @Headers({"Content-Type: application/json"})
    @GET(ApiEndPoint.GetProject)
    Call<ApiResponse<List<GetProjectModel>>> getProject();

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.GetBlocks)
    Call<ApiResponse<List<GetBlockModel>>> getBlocks(@Query("projectId") String projectId);

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.PlotAvailability)
    Call<ApiResponse<List<PlotAvailableModel>>> getPlotAvailabilitylistfilter(@Query("projectId") String projectId,
                                                                              @Query("blockId") String blockId);
    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.ChangeMobileNo)
    Call<EditMobileModel>getEditMobile(@Query("loginId")String loginId, @Query("MobileNo")String MobileNo,
                                                               @Query("RoleId")Integer RoleId );
    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.ChangeEmailId)
    Call<EditEmailModel>getEditEmail(@Query("loginId")String loginId, @Query("EmailId")String EmailId,
                                                      @Query("RoleId")Integer RoleId);

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.Profile)
    Call<ApiResponse<List<GetProfileModel>>> getProfile(@Query("LoginId") String LoginId, @Query("RoleId") Integer RoleId);

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.GetMyGoal)
    Call<ApiResponse<List<MyGoalListModel>>> getMyGoalListModel(@Query("LoginId") String LoginId,@Query("roleId") Integer roleId);

    @Headers({"Content-Type: application/json"})
    @GET(ApiEndPoint.GetAllLead)
    Call<ApiResponse<List<LeadListModel>>> getLeadListModel();

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.GetLead)
    Call<ApiResponse<List<LeadListModel>>>getGoLeadListModel(@Query("requesterName") String requesterName);

    @Headers({"Content-Type: application/json"})
    @GET(ApiEndPoint.GetAllCustomer)
    Call<ApiResponse<List<CustomerListModel>>> getCustomerListModel();

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.GetCustomer)
    Call<ApiResponse<List<CustomerListModel>>>getGoCustomerListModel(@Query("Name") String requesterName,
                                                                     @Query("EmailId")String EmailId,
                                                                     @Query("Mobile")String Mobile);

    @Headers({"Content-Type: application/json"})
    @GET(ApiEndPoint.GetAllLeadFollowUP)
    Call<ApiResponse<List<FollowUpListModel>>>getFollowUpListModel();

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.GetLeadFollowUP)
    Call<ApiResponse<List<FollowUpListModel>>>getGoFollowUpListModel(@Query("requesterName") String requesterName);

    @Headers({"Content-Type: application/json"})
    @POST(ApiEndPoint.FollowUp)
    Call<FollowUpModel>getFollowUpModel(@Query("LoginId") String LoginId,
                                                           @Query("bigIntLeadId") String bigIntLeadId,
                                                           @Query("FollowUpDate")String FollowUpDate,
                                                           @Query("NoteFollowUp")String NoteFollowUp);


}
