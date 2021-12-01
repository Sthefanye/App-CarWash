package com.example.carwash.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class Util {



    companion object aquiVaiMetodosEstaticos {

        //caso seja necessário alguma permissão do dispositivo
        fun permissao(activity: Activity?, requestCode: Int, permissoes: Array<String>): Boolean {

            val list: MutableList<String> = ArrayList()
            //No nosso for a variavel permissao irá receber de uma em uma as strings do array da variavel permissoes
            for (permissao in permissoes) {

                //caso o usuario NÃO tenha permitido ao aplicativo certos privilegios de acesso
                //a variavel ok recebera false
                val ok = ContextCompat.checkSelfPermission(activity!!, permissao) == PackageManager.PERMISSION_GRANTED

                if (!ok) { // caso a variavel ok tenha recebido false, então cairá dentro desse if
                    // a variavel list irá receber a nossa string do for "que simboliza a permissao que
                    // o aplicativo está querendo"
                    list.add(permissao)
                }

            } // fim do nosso for - caso a variavel permissoes tenha mais de uma string,o for é executado novamente


            //se a nossa variavel list for vazia quer dizer que o usuario já permitiu que o nosso aplicativo
            //tenha certos privilegios e então entrará dentro no nosso if
            if (list.isEmpty()) {
                return true // caso entre dentro do nosso if o metodo acaba aqui
            }

            //solicita a permissao
            ActivityCompat.requestPermissions(activity!!, list.toTypedArray(), requestCode)
            return false
        }



        fun exibirToast(context: Context,mensagem: String){

            Toast.makeText(context, mensagem , Toast.LENGTH_LONG).show()
        }


        fun statusInternet(context: Context): Boolean {
            val conexao =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (conexao != null) {
                // PARA DISPOSTIVOS NOVOS
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    val recursosRede = conexao.getNetworkCapabilities(conexao.activeNetwork)

                    if (recursosRede != null) { //VERIFICAMOS SE RECUPERAMOS ALGO
                        if (recursosRede.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                            //VERIFICAMOS SE DISPOSITIVO TEM 3G
                            return true
                        } else if (recursosRede.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                            //VERIFICAMOS SE DISPOSITIVO TEM WIFFI
                            return true
                        }

                        //NÃO POSSUI UMA CONEXAO DE REDE VÁLIDA
                        return false
                    }
                } else { //COMECO DO ELSE

                    // PARA DISPOSTIVOS ANTIGOS  (PRECAUÇÃO)
                    val informacao = conexao.activeNetworkInfo

                    return informacao != null && informacao.isConnected
                } //FIM DO ELSE
            }
            return false
        }



    




    }



}