package worten.aebd.com.worten;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Login extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    private Button enter;

    private boolean cambio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),3);

        enter = (Button) findViewById(R.id.entrar);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.initSesion();
                CharSequence texto = "Has añadido iniciado sesión correctamente.";
                Toast toast = Toast.makeText(Login.this, texto, Toast.LENGTH_LONG);
                toast.show();
                Intent mainIntent = new Intent();
                mainIntent = new Intent().setClass(
                        Login.this, Shop.class);
                startActivity(mainIntent);
                finish();

            }
        });

        int t = (Session.isLogin() ? View.VISIBLE:View.INVISIBLE);



    /*
        mListView = (ListView) findViewById(R.id.product_label);
        mListView.setAdapter(new ArrayAdapter<String>(getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, categorias));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                String elegido = (String) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido;
                Toast toast = Toast.makeText(Shop.this, texto, Toast.LENGTH_LONG);
                toast.show();
            }
        });*/

        
        

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();

    }

    public void onSectionAttached(int number) {
        if(cambio){
            Intent mainIntent = new Intent();
        if(Session.isLogin()){
        switch (number) {
            case 1:
                mainIntent = new Intent().setClass(
                        Login.this, Shop.class);
                startActivity(mainIntent);
                break;
            case 2:
                mainIntent = new Intent().setClass(
                        Login.this, Scaner.class);
                startActivity(mainIntent);
                break;
            case 3:
                mainIntent = new Intent().setClass(
                        Login.this, Games.class);
                startActivity(mainIntent);
                break;
        }}else{
            CharSequence texto = "Necesitas iniciar sesión para eso.";
            switch (number) {
                case 1:
                    mainIntent = new Intent().setClass(
                            Login.this, Shop.class);
                    startActivity(mainIntent);
                    break;
                case 2:

                    Toast toast = Toast.makeText(Login.this, texto, Toast.LENGTH_LONG);
                    toast.show();
                    break;
                case 3:
                    Toast toast2 = Toast.makeText(Login.this, texto, Toast.LENGTH_LONG);
                    toast2.show();
                    break;
                case 4:
                    Toast toast3 = Toast.makeText(Login.this, texto, Toast.LENGTH_LONG);
                    toast3.show();
                    break;
            }
        }
        }else{
            cambio = true;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            getMenuInflater().inflate(R.menu.shop, menu);
            menu.getItem(1).setVisible(Session.isLogin());
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.close_settings) {

            Session.closeSesion();

            CharSequence texto = "Has cerrado sesion";
            Toast toast = Toast.makeText(Login.this, texto, Toast.LENGTH_LONG);
            toast.show();

            Intent mainIntent = new Intent();
            mainIntent = new Intent().setClass(
                    Login.this, Shop.class);
            startActivity(mainIntent);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_shop, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Login) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
