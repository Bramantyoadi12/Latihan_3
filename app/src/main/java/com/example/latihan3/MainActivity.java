package com.example.latihan3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    RadioGroup RG1, RG2, RG3, RG4, RGjk;
    RadioButton lk, pr, islam, kristen, katolik, hindu, buddha, konghucu, aliran;
    EditText ETNamaDpn, ETNamaBlkng, ETTmptL, ETDate, ETAlmt, ETTlp, ETEmail, ETPass, ETPassUlang;
    Button BtnDaftar, BtnKembali, BtnOk, BtnKeluar;

    String Bc, jk, agama, NamaDpn, NamaBlkng, Tempat, Tanggal, Alamat, Telpon, Email, Password, PassUlang;
    TextView TvNamaDpn, TvNamaBlkng, TvTTL, TvAlamat, Tvjk, Tvagama, TvTlp, TvEmail;

    int tahun, bulan, hari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETNamaDpn = findViewById(R.id.ETNamaDpn);
        ETNamaDpn = findViewById(R.id.ETNamaBlkng);
        ETTmptL = findViewById(R.id.ETTmptL);
        ETDate = findViewById(R.id.ETDate);
        ETAlmt = findViewById(R.id.ETAlmt);
        ETTlp = findViewById(R.id.ETTlp);
        ETEmail = findViewById(R.id.ETEmail);
        ETPass = findViewById(R.id.ETPass);

        RGjk = findViewById(R.id.RGjk);
        RG1 = findViewById(R.id.RG1);
        RG2 = findViewById(R.id.RG2);
        RG3 = findViewById(R.id.RG3);
        RG4 = findViewById(R.id.RG4);

        lk = findViewById(R.id.lk);
        pr = findViewById(R.id.pr);
        islam = findViewById(R.id.islam);
        kristen = findViewById(R.id.kristen);
        katolik = findViewById(R.id.katolik);
        hindu = findViewById(R.id.hindu);
        buddha = findViewById(R.id.buddha);
        konghucu = findViewById(R.id.konghucu);
        aliran = findViewById(R.id.aliran);

        BtnDaftar = findViewById(R.id.BtnDaftar);
        BtnKembali = findViewById(R.id.BtnKembali);

        Bc = BuildConfig.FLAVOR;
        ETEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!ETEmail.getText().toString().trim()
                        .matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    ETEmail.setError("Format Email Salah");
                }
            }
        });

        ETDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePick();
            }
        });
        BtnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValidEditText();
                isValidPassword();
            }
        });
        BtnKembali.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //----------------------------------------------Validasi----------------------------------------
    public void isValidEditText() {
        NamaDpn = ETNamaDpn.getText().toString();
        NamaBlkng = ETNamaBlkng.getText().toString();
        Tempat = ETTmptL.getText().toString();
        Tanggal = ETDate.getText().toString();
        Alamat = ETAlmt.getText().toString();
        Telpon = ETTlp.getText().toString();
        Email = ETEmail.getText().toString();
        Password = ETPass.getText().toString();
        PassUlang = ETPass.getText().toString();

        if (NamaDpn.isEmpty() && NamaBlkng.isEmpty() && Tempat.isEmpty() && Tanggal.isEmpty() &&
                Alamat.isEmpty() && Telpon.isEmpty() && Email.isEmpty() && Password.isEmpty() &&
                PassUlang.isEmpty()) {
            ETNamaDpn.setError("Nama Depan Harus Diisi");
            ETNamaBlkng.setError("Nama Belakang Harus Diisi");
            ETTmptL.setError("Tempat Lahir Harus Diisi");
            ETDate.setError("Tanggal Lahir Harus Diisi");
            ETAlmt.setError("Alamat Harus Diisi");
            ETTlp.setError("Nomor Telepon Harus Diisi");
            ETEmail.setError("Email Harus Diisi");
            ETPass.setError("Password Harus Diisi");
            ETPassUlang.setError("Password Harus Diisi");
        } else {
            konfirmasi();
        }
    }

    public boolean pattern(String pass) {
        String patt = "^(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$).{4,}" +
                "$";
        return Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$).{4,}" +
                "$").matcher(pass).matches();
    }

    public void isValidPassword() {
        String err = "Panjang password minimal adalah 8 karakter dengan angka, huruf " +
                "besar, huruf kecil, dan spesial karakter (seperti: @,&,#,?)";
        if (TextUtils.isEmpty(ETPass.getText().toString().trim()) ||
                ETPass.getText().toString().trim().length() < 8) {
            ETPass.setError(err);
        } else if (!pattern(ETPass.getText().toString().trim())) {
            ETPass.setError(err);
        } else {
            if (!ETPass.getText().toString().trim().equals(
                    ETPassUlang.getText().toString().trim())) {
                ETPassUlang.setError("Password harus sama");
            }
        }
    }

    //-----------------------------------------View Hasil-------------------------------------------
    public void HasilPendaftaran() {
        final AlertDialog aDB = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.hasil_pendaftaran, null);
        aDB.setTitle("Detail Pendaftaran");
        aDB.setView(dialogView);
        aDB.setCancelable(true);
        aDB.setIcon(R.mipmap.ic_launcher);

        TvNamaDpn = dialogView.findViewById(R.id.TvNamaDpn);
        TvNamaBlkng = dialogView.findViewById(R.id.TvNamaBlkng);
        TvTTL = dialogView.findViewById(R.id.TvTTL)
        TvAlamat = dialogView.findViewById(R.id.TvAlmt)
        TvEmail = dialogView.findViewById(R.id.TvEmail);
        TvTlp = dialogView.findViewById(R.id.TvTlp);
        Tvjk = dialogView.findViewById(R.id.Tvjk);
        Tvagama = dialogView.findViewById(R.id.Tvagama);

        BtnOk = dialogView.findViewById(R.id.BtnOke);
        BtnKeluar = dialogView.findViewById(R.id.BtnKeluar);

        TvNamaDpn.setText(NamaDpn);
        TvNamaBlkng.setText(NamaBlkng);
        TvAlamat.setText(Alamat);
        Tvagama.setText(agama);
        TvEmail.setText(Email);
        TvTlp.setText(Telpon);
        Tvjk.setText(jk);

        StringBuilder sB = new StringBuilder();
        sB.append(Tempat);
        sB.append(", ");
        sB.append(Tanggal);
        TvTTL.setText(sB.toString());

        BtnOk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pendaftaran Selesai",
                        Toast.LENGTH_LONG).show();
                aDB.dismiss();
            }
        });

        BtnKeluar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        aDB.show();
    }

    //    ------------------------------------konfirmasi--------------------------------------------
    private void konfirmasi() {
        final AlertDialog.Builder aDB = new AlertDialog.Builder(this);
        aDB.setTitle("Detail Pendaftaran");
        aDB.setCancelable(true);
        aDB.setIcon(R.mipmap.ic_launcher);
        aDB.setMessage("Apakah data yang anda masukkan sudah benar?");

        aDB.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HasilPendaftaran();
            }
        });

        aDB.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog aD = aDB.create();

        aD.show();
    }

    //----------------------------------------Pilih tanggal-----------------------------------------
    public void datePick() {
        Calendar c = Calendar.getInstance();
        tahun = c.get(Calendar.YEAR);
        bulan = c.get(Calendar.MONTH);
        hari = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dPD = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int _tahun, int _bulan, int _hari) {
                        ETDate.setText(_hari + "-" + (_bulan + 1) + "-" + _tahun);
                    }
                }, tahun, bulan, hari
        );

        dPD.show();
    }

    //----------------------------------Choose Radio Button-----------------------------------------
    public void ChooseRB(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.lk:
                if (checked) {
                    jk = "Laki-Laki";
                }
                break;
            case R.id.pr:
                if (checked) {
                    jk = "Perempuan";
                }
                break;
            case R.id.islam:
                if (checked) {
                    agama = "Islam";
                    RG2.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.kristen:
                if (checked) {
                    agama = "Kristen";
                    RG2.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.hindu:
                if (checked) {
                    agama = "Hindu";
                    RG1.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.buddha:
                if (checked) {
                    agama = "Buddha";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.katolik:
                if (checked) {
                    agama = "Katolik";
                    RG1.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.konghucu:
                if (checked) {
                    agama = "Konghucu";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.aliran:
                if (checked) {
                    agama = "Aliran Kepercayaan";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG3.clearCheck();
                }
                break;
            default:
                break;
        }
    }

}
