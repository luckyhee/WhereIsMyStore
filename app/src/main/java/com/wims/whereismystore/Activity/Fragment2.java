package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Final_Project.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class Fragment2 extends Fragment  {
    //현황기능 class

    //private SupportMapFragment mapFragment;
    private GoogleMap map;
    private View view;
    private Spinner localspin;
    private Spinner districtspin;
    private ArrayAdapter<CharSequence> loadspin, diadspin;
    private String province, city;
    private String address_string;
    String jsonString;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);


        localspin = (Spinner) view.findViewById(R.id.load_local);
        districtspin = (Spinner) view.findViewById(R.id.load_district);
        Button address_button = (Button) view.findViewById(R.id.address_button);

        loadspin = ArrayAdapter.createFromResource(getActivity(), R.array.local, R.layout.support_simple_spinner_dropdown_item);
        loadspin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        localspin.setAdapter(loadspin);
        localspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (loadspin.getItem(position).equals("서울특별시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.서울특별시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("부산광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.부산광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("대구광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.대구광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("인천광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.인천광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("광주광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.광주광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("대전광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.대전광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("울산광역시")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.울산광역시, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("경기도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경기도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("강원도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.강원도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("충청북도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.충청북도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("충청남도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.충청남도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("전라북도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.전라북도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("전라남도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.전라남도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("경상북도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경상북도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("경상남도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경상남도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else if (loadspin.getItem(position).equals("제주특별자치도")) {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.제주특별자치도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                } else {
                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.세종특별자치도, R.layout.support_simple_spinner_dropdown_item);
                    districtspin.setAdapter(diadspin);
                }

                // 하위 스피너 선택 시 :D
                districtspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        city = (String) districtspin.getSelectedItem();
                        province = (String) localspin.getSelectedItem();
                        address_string = province + " " + city;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Geocoder geocoder = new Geocoder(getContext());
        final SupportMapFragment mapFragment = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                // 서울 여의도에 대한 위치 설정
                LatLng seoul = new LatLng(37.52487, 126.92723);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,13));
            }
        });
        mapFragment.getView().setVisibility(View.GONE);
        JsonParse jsonParse = new JsonParse();
        jsonParse.execute("http://220.66.144.32/wims/test.php");

        address_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 주소입력후 지도2버튼 클릭시 해당 위도경도값의 지도화면으로 이동
                List<Address> list = null;
                String str = address_string;
                try {
                    list = geocoder.getFromLocationName(str, // 지역 이름
                            10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {
                    } else {
                        // 해당되는 주소로 인텐트 날리기
                        Address addr = list.get(0);
                        double lat = addr.getLatitude();
                        double lon = addr.getLongitude();
                        LatLng latlng = new LatLng(lat, lon);

                          map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,13));
//                        JsonParse jsonParse = new JsonParse();
////                        jsonParse.execute("http://220.66.144.32/wims/test.php",str);
//                        jsonParse.execute("http://220.66.144.32/wims/test.php");
//                        synchronized(jsonParse){
//                            try{
//                                // b.wait()메소드를 호출.
//                                // 메인쓰레드는 정지
//                                // ThreadB가 5번 값을 더한 후 notify를 호출하게 되면 wait에서 깨어남
//                                System.out.println("b가 완료될때까지 기다립니다.");
//                                jsonParse.wait();
//                            }catch(InterruptedException e){
//                                e.printStackTrace();
                            }
                        }
                        mapFragment.getView().setVisibility(View.VISIBLE);
//                    }
//                }
            }
        });
        return view;
    }

//    private void Spinner()  {
//        localspin = (Spinner) view.findViewById(R.id.load_local);
//        districtspin = (Spinner) view.findViewById(R.id.load_district);
//
//        loadspin = ArrayAdapter.createFromResource(getActivity(), R.array.local, R.layout.support_simple_spinner_dropdown_item);
//        loadspin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//
//        localspin.setAdapter(loadspin);
//        localspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (loadspin.getItem(position).equals("서울특별시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.서울특별시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("부산광역시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.부산광역시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("대구광역시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.대구광역시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("인천광역시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.인천광역시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("광주광역시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.광주광역시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("대전광역시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.대전광역시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("울산광역시")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.울산광역시, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("경기도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경기도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("강원도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.강원도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("충청북도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.충청북도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("충청남도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.충청남도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("전라북도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.전라북도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("전라남도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.전라남도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("경상북도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경상북도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("경상남도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.경상남도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else if (loadspin.getItem(position).equals("제주특별자치도")) {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.제주특별자치도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                } else {
//                    diadspin = ArrayAdapter.createFromResource(getActivity(), R.array.세종특별자치도, R.layout.support_simple_spinner_dropdown_item);
//                    districtspin.setAdapter(diadspin);
//                }
//
//                // 하위 스피너 선택 시 :D
//                districtspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                        city = (String) districtspin.getSelectedItem();
//                        province = (String) localspin.getSelectedItem();
//                        address_string = province + " " + city;
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }

    public class JsonParse extends AsyncTask<String,Void,String> {
        String TAG = "JsonParseTest";
        @Override
        protected  String doInBackground(String...strings){
            String url = strings[0];
//            String address = strings[1] + "%";
            try{
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)serverURL.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                outputStream.write(address.getBytes("UTF-8"));
//                outputStream.flush();
//                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();
                }

                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null)
                    sb.append(line);

                bufferedReader.close();
                Log.d(TAG, sb.toString().trim());

                return sb.toString().trim();
            } catch (Exception e){
                Log.d(TAG,"InsertData:Error",e);
                String errorString = e.toString();

                return null;
            }
        }

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected  void onProgressUpdate(Void... values ){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String fromdoInBackgroundString) {
            super.onPostExecute(fromdoInBackgroundString);

            if(fromdoInBackgroundString == null) {
                Log.i("PHP 에러","error");
            }
            else{
                jsonString = fromdoInBackgroundString;
                try{
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject item = jsonArray.getJSONObject(i);
                        String name =  item.getString("개방서비스명");
                        String state = item.getString("영업상태명");
                        String address = item.getString("도로명전체주소");
                        String latS = item.getString("좌표x");
                        String langS = item.getString("좌표y");
                       Toast.makeText(getActivity(), name + " | " + state + " | " + latS + " | " + langS, Toast.LENGTH_SHORT).show();
                        double lat = Double.parseDouble(latS);
                        double lang = Double.parseDouble(langS);
//
                        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(lat,lang)).title(name).snippet(state);
                        map.addMarker(markerOptions).showInfoWindow();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }
}