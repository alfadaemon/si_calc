/*
 * Copyright (C) 2013 Code Here Now - A subsidiary of Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.codeherenow.sicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SICalculatorActivity extends Activity {
	
	private EditText principal, interest;
	private SeekBar seek_years;
	private TextView txt_result, txt_principal, txt_interest;
	private TextView txt_yearstxt;
	private Button btn_calculate;
	 
	public TextView txt_years;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sicalculator);
		
		principal  = (EditText) findViewById(R.id.edit_principal);
		interest   = (EditText) findViewById(R.id.edit_interest);
		txt_years  = (TextView) findViewById(R.id.text_years);

		txt_principal = (TextView) findViewById(R.id.text_principal);
		txt_principal.setText(Html.fromHtml(getString(R.string.principal)));
		
		txt_interest = (TextView) findViewById(R.id.text_interest);
		txt_interest.setText(Html.fromHtml(getString(R.string.interest)));
		
		txt_yearstxt  = (TextView) findViewById(R.id.text_yeartxt);
		txt_yearstxt.setText(Html.fromHtml(getString(R.string.years)));
		
		txt_result = (TextView) findViewById(R.id.text_result);
		txt_result.setText(Html.fromHtml(getString(R.string.result)));
		
		btn_calculate  = (Button) findViewById(R.id.btn_calculate);
		btn_calculate.setText(Html.fromHtml(getString(R.string.calculate)));
		
		seek_years = (SeekBar) findViewById(R.id.seek_years);
		
		btn_calculate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Double result, ammount, interest_rate;
				int years;
				
				ammount = Double.parseDouble(principal.getText().toString());
				interest_rate = Double.parseDouble(interest.getText().toString());
				years = seek_years.getProgress(); 
				
				String str_result = getString(R.string.result);
				switch (v.getId()) {
					case R.id.btn_calculate:
						result = Calculate(ammount, interest_rate, years);
						txt_result.setText(Html.fromHtml(String.format(str_result, 
								ammount, interest_rate, years, result)));
						break;
				}
			}
			
			private Double Calculate(Double principal, Double interest, int years){
				return principal * (interest/100) * years;
			}
		}
		);
		
		seek_years.setOnSeekBarChangeListener( new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				txt_years.setText(String.valueOf(progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			}
		);
	}
}
