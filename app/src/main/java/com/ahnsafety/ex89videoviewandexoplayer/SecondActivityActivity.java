package com.ahnsafety.ex89videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class SecondActivityActivity extends AppCompatActivity {

    //video Uri
    Uri videoUri=Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");

    PlayerView pv;
    //실제 비디오를 플레이하는 객체의 참조변수
    SimpleExoPlayer player;

    //컨트롤러 뷰 참조변수
    PlayerControlView pcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        pv=findViewById(R.id.pv);
        pcv=findViewById(R.id.pcv);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //SimpleExoPlayer객체 생성 (진행바가 있는 객체)
        player= ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());

        //플레이어뷰에게 플레이어 설정
        pv.setPlayer(player);

        //플레이어컴트롤뷰와 플레이어 연동
        pcv.setPlayer(player);

        //비디오데이터 소스를 관리하는 DataSource 객체를 만들어주는 팩토리 객체 생성
        DataSource.Factory factory=new DefaultDataSourceFactory(this,"Ex89VideoAndExoPlayer");
        //비디오데이터를 Uri로 부터 추출해서 DataSource객체(CD or LP판 같은) 생성!!
        ProgressiveMediaSource mediaSource= new ProgressiveMediaSource.Factory(factory).createMediaSource(videoUri);

        //만들어진 비디오데이터 소스객체인 mediaSource를
        //플레이어 객체에게 전달하여 준비하도록!![로딩하도록]
        player.prepare(mediaSource);

        //로딩이 완료되어 준비가 되었을 때
        //자동 실행되도록..
        //player.setPlayWhenReady(true);
    }
    //화면에서 안ㅂ일 때 ..

    @Override
    protected void onStop() {
        super.onStop();

        //플레이어뷰 및 플레이어 객체 초기화
        pv.setPlayer(null);
        player.release();
        player=null;
    }
}
