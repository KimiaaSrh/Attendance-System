package ir.ac.shirazu.attendance_system.Api;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;


import ir.ac.shirazu.attendance_system.Models.User;

public class UserApi {

    private static JsonObject jsonObject;
    private static boolean bool;
    private static SharedPreferences shPref;
    private static SharedPreferences.Editor sEdit;

    public static JsonObject getJsonObject() {
        return jsonObject;
    }

    public static void signup(User user, Context context){
        jsonObject =null;
        bool = false;

        Ion.getDefault(context).configure().setLogging("MyLogs", Log.DEBUG);
        Ion.with(context)
                .load(Config.userRoute + "/signup")
                .setHeader("Content-Type", "application/json")
                .setJsonObjectBody(user.getJson())
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if(e != null){
                            e.printStackTrace();
                            return;
                        }
                        jsonObject = result.getResult();
                        System.out.println("signup" + jsonObject);

                        //write your code here


                        //
                    }
                });
    }

    public static void login(User user, final Context context){
        jsonObject =null;
        bool = false;

        shPref = context.getSharedPreferences(Config.MyPref, Context.MODE_PRIVATE);
        sEdit = shPref.edit();

        Ion.getDefault(context).configure().setLogging("MyLogs", Log.DEBUG);
        Ion.with(context)
                .load("POST", Config.userRoute + "/login")
                .setHeader("Content-Type", "application/json")
                .setJsonObjectBody(user.getJson())
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if(e != null){
                            e.printStackTrace();
                            return;
                        }
                        jsonObject = result.getResult();
                        if(jsonObject.get("message").getAsString().equals(Config.AUTH_SUCCESSFUL)){
                            sEdit.putString(Config.token, jsonObject.get("token").getAsString());
                            sEdit.apply();
                        }
//                        System.out.println(shPref.getString(Config.token, null));
                        System.out.println("login" + jsonObject);


                        //write your code here
                        checkAuth(context);

                        //

                    }
                });
    }

    public static void checkAuth(Context context){
        jsonObject =null;
        bool = false;

        shPref = context.getSharedPreferences(Config.MyPref, Context.MODE_PRIVATE);

        Ion.getDefault(context).configure().setLogging("MyLogs", Log.DEBUG);
        Ion.with(context)
                .load("GET", Config.userRoute + "/auth")
                .setHeader(Config.token, "Bearer "+ shPref.getString(Config.token, ""))
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if(e != null){
                            e.printStackTrace();
                            return;
                        }
                        jsonObject = result.getResult();

                        if (jsonObject.get("message")!=null && jsonObject.get("message").getAsString().equals(Config.AUTH_SUCCESSFUL)){
                            bool = true;
                        }

                        System.out.println( "checkAuth" + " auth:"+ bool + "  " + jsonObject);

                        //write your code here


                        //
                    }
                });
    }

}
