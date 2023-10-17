package com.example.asm_ph45090;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.example.asm_ph45090.Adapter.AdapterNhanSu;
import com.example.asm_ph45090.Dialog.NsDialog;
import com.example.asm_ph45090.Model.ModelNhanSu;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class NsActivity extends AppCompatActivity implements NsDialog.NsDialogListener {
    MaterialToolbar topAppBarNS;
    ListView lsvNS;
//    ImageButton btnAddNS;
    ArrayList<ModelNhanSu> dsNS = new ArrayList<>();
    AdapterNhanSu adapterNhanSu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ns_activity);
//        btnAddNS = findViewById(R.id.btn_addNS);

        lsvNS = findViewById(R.id.lsvNS);

        //Kiểm tra file đã tồn tại chưa , nếu chưa thì tạo file
        File file = new File(NsActivity.this.getFilesDir(), "eml.txt");
        if (!file.exists()) {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(NsActivity.this.openFileOutput("eml.txt", MODE_PRIVATE));
                String data = "ModelNhanSu{id=0, MaNV='NV01', HoTen='Nguyen Van A', PhongBan='Hành chính'}\n" +
                        "ModelNhanSu{id=1, MaNV='NV02', HoTen='Nguyen Van A', PhongBan='Nhân Sự'}\n" +
                        "ModelNhanSu{id=2, MaNV='NV03', HoTen='Nguyen Van A', PhongBan='Đào tạo'}\n";
                outputStreamWriter.write(data);
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //Dọc file eml.txt sau đó thêm data vào dsNS
        try {
            InputStream inputStream = NsActivity.this.openFileInput("eml.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] parts = receiveString.split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String maNV = parts[1].split("=")[1].replace("'", "");
                    String hoTen = parts[2].split("=")[1].replace("'", "");
                    String phongBan = parts[3].split("=")[1].replace("'", "").replace("}", "");

                    ModelNhanSu nhanSu = new ModelNhanSu(id, maNV, hoTen, phongBan);
                    dsNS.add(nhanSu);
                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        adapterNhanSu = new AdapterNhanSu(this , dsNS);
        lsvNS.setAdapter(adapterNhanSu);


        topAppBarNS = findViewById(R.id.topAppBarNS);
        setSupportActionBar(topAppBarNS);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

//        btnAddNS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onOpenAddDialog();
//            }
//        });
    }

    private void onOpenAddDialog() {
        NsDialog nsDialog = new NsDialog();
        nsDialog.show(getSupportFragmentManager(), "add");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.ns_activity_menu ,menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        MenuItem mnuAdd = menu.findItem(R.id.mnu_add);
        MenuItem mnuSearch = menu.findItem(R.id.mnu_search);


        SearchView searchView = new SearchView(new ContextThemeWrapper(this , R.style.SearchViewStyle));
        mnuSearch.setActionView(searchView);
        if (searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }
        MenuItemCompat.setOnActionExpandListener(mnuSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //khi nút tìm kiếm mở rộng thì ẩn nút thêm
                mnuAdd.setVisible(false);
                return true; // return true để xử lý sự kiện
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //khi nút tìm kiếm thu nhỏ thì hiện nút thêm
                mnuAdd.setVisible(true);
                return true;
            }
        });

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterNhanSu.getFilter().filter(s);
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void applyText(String newId, String newName, String newPB) {
        ModelNhanSu newNS = new ModelNhanSu(dsNS.size() + 1 , newId , newName , newPB );
        dsNS.add(newNS);
        adapterNhanSu.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id_menu_selected = item.getItemId();
        if (id_menu_selected == R.id.mnu_add){
            onOpenAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}
