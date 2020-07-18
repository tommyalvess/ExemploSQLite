package br.com.fiap.exemplosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

      ClienteDAO clienteDAO;
      ListView lstClientes;
      List<Cliente> clientes = new ArrayList<>();

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            clienteDAO = new ClienteDAO(this);
            lstClientes = (ListView) findViewById(R.id.lstClientes);
            lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Cliente cliente = clientes.get(i);
                        Intent it = new Intent(MainActivity.this, VisualizarActivity.class);
                        it.putExtra("cliente", cliente);
                        startActivity(it);
                  }
            });
      }

      @Override
      protected void onResume() {
            super.onResume();
            atualizarLista();
      }

      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = new MenuInflater(this);
            menuInflater.inflate(R.menu.menu_main, menu);

            return super.onCreateOptionsMenu(menu);
      }

      @Override
      public boolean onOptionsItemSelected(MenuItem item) {

            if ( item.getItemId() == R.id.mnCadastrar ) {
                  Intent it = new Intent(this, CadastroActivity.class);
                  startActivity(it);
            }

            return super.onOptionsItemSelected(item);
      }

      public void atualizarLista() {
            clientes = clienteDAO.all();

            ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(
                    this, android.R.layout.simple_list_item_1, clientes);

            lstClientes.setAdapter(adapter);
      }
}
