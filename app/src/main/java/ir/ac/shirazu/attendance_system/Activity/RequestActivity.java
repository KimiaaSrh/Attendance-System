package ir.ac.shirazu.attendance_system.Activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import ir.ac.shirazu.attendance_system.Activity.Date.DatePickerDialog;
import ir.ac.shirazu.attendance_system.Activity.CalendarUtils.PersianCalendar;

import ir.ac.shirazu.attendance_system.R;

import static ir.ac.shirazu.attendance_system.Activity.CalendarUtils.LanguageUtils.getPersianNumbers;

public class RequestActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public int flag = 0;
    public String usernameString;
    public String employeeIDString;
    public String spinnerChosen;
    public String startDate;
    public String endDate;
    public String description;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_admission);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        final Drawable backArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.mdtp_white), PorterDuff.Mode.SRC_ATOP);
        mToolbar.setNavigationIcon(backArrow);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Spinner spinner = (Spinner) findViewById(R.id.request_type);
        TextView usernameTextView = (TextView) findViewById(R.id.full_name);
        TextView employeeIDTextView = (TextView) findViewById(R.id.employee_id);
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        Button button = (Button) findViewById(R.id.request_button);
         /*
        MAYBE USEFUL HINT:
            ArrayAdapter if choices are available in an array
            CursorAdapter if choices are available from a database query
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.requests_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        fillDateTextView();


        spinnerChosen = spinner.getSelectedItem().toString();
        usernameString = usernameTextView.getText().toString();
        employeeIDString = employeeIDTextView.getText().toString();
        description = descriptionTextView.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*

                 */
            }
        });
    }

    private void fillDateTextView(){
        PersianCalendar persianCalendar = new PersianCalendar();
        TextView fromDateTextView = (TextView) findViewById(R.id.date_from);
        TextView toDateTextView = (TextView) findViewById(R.id.date_to);

        String date = getPersianNumbers(persianCalendar.getPersianYear()+"/"+(persianCalendar.getPersianMonth()+1)+"/"+persianCalendar.getPersianDay());
        fromDateTextView.setText(date);
        toDateTextView.setText(date);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth){
        String date = getPersianNumbers(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);


        if(flag == 1){
            TextView dateTextView = (TextView) findViewById(R.id.date_from);
            dateTextView.setText(date);
            startDate = date;
        }else if(flag == 2){
            TextView dateTextView = (TextView) findViewById(R.id.date_to);
            dateTextView.setText(date);
            endDate = date;
        }
        flag = 0;

    }

    public void onDateToClick(View v){
        flag = 2;
        PersianCalendar persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(RequestActivity.this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay());
        datePickerDialog.show(getFragmentManager(),"DatePickerDialog");
    }

    public void onDateFromClick(View v){
        flag = 1;
        PersianCalendar persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(RequestActivity.this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay());
        datePickerDialog.show(getFragmentManager(),"DatePickerDialog");
    }

}
