package soc.ms.msuser.interfaces;

public class Constants {
    public static final String UserControllerHeader = "/api/user";
    public static final String UserGetAll = "/get_users";
    public static final String UserGet = "/get_user";
    public static final String UserGetCommunities = "/get_communities";
    public static final String UserJoinCommunity = "/join_community";
    public static final String UserLeaveCommunity = "/leave_community";

    public static final String CommunityControllerHeader = "/api/community";
    public static final String CommunityGetAllCommunities = "/get_all_communities";
    public static final String CommunityGetInfo = "/get_info";
    public static final String CommunitySetInfo = "/set_info";
    public static final String CommunityGetAllMembers = "/get_all_members";
    public static final String CommunitySendEvent = "/send_event";

    public static final String CommunityCommentControllerHeader = "/api/community_comment";
    public static final String CommunityCommentGetAllComments = "/get_all";
    public static final String CommunityCommentGetAllFlatComments = "/get_all_flat";
    public static final String CommunityCommentGetComment = "/get";
    public static final String CommunityCommentGetFlatComment = "/get_flat";
    public static final String CommunityCommentAdd = "/add";
    public static final String CommunityCommentPin = "/pin";
    public static final String CommunityCommentDelete = "/delete";
    public static final String CommunityCommentChange = "/change";
    public static final String CommunityCommentReact = "/react";
    public static final String CommunityCommentReply = "/reply";

    public static final String UserResponsableDemandeHeader = "/api/user_demandes";
    public static final String UserResponsableDemandeGetAll = "/get_all";
    public static final String UserResponsableDemandeGet = "/get";
    public static final String UserResponsableDemandeAdd = "/add";
    public static final String UserResponsableDemandeDeny = "/deny";
    public static final String UserResponsableDemandeAccept = "/accept";
}
