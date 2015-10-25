<%@page import="modelo.Usuario"%>
<%@page import="modelo.actividad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Agenda</title>
        
        <script src="librerias/angular.js"></script>
        <script src="miAplicacion.js"></script>
        <script src="librerias/jquery.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
        <script src="librerias/jquery.stickyheader.js"></script>
        
            
        <meta charset="UTF-8">
        <link text="text/css" rel="stylesheet" href="estilo.css"> 
    </head>
    <body ng-app="miAplicacion" ng-controller="Principal">
        <%
                    String tex = (String)request.getAttribute("id");
                    
                    ArrayList<actividad> actividades = (ArrayList<actividad>)request.getAttribute("actividades");
                    String ListaActividades=new Gson().toJson(actividades);
                    
                    
                    
                    
        %>
        <div class="agenda">
            <table>
                <thead>
                    <tr>
                        <th>Horas</th>
                        <th ng-repeat="dia in dias" id="{{ dia }}" class="columnas">{{ dia }}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="hora in horas">
                        <th>{{ hora | date:"HH:mm" }}</th>
                        <td ng-repeat="dia in dias" id="{{ dia }}{{ hora | date:'HH:mm' }}">
                            <button ng-click="inicio(dia,hora)">+</button>
                            <button ng-click="fin(dia,hora)">-</button>
                            <section>
                                <div ng-if="actividades[dia][hora].nombre">
                                    Nombre: <input type="text" ng-model="actividades[dia][hora].nombre"></input><br>
                                    Descripción: <input type="text" ng-model="actividades[dia][hora].descripcion"></input><br>
                                    Avance: 
                                    <select ng-model="actividades[dia][hora].avance">
                                        <option value="No iniciada">No iniciada</option>
                                        <option value="En curso">En curso</option>
                                        <option value="Terminada">Terminada</option>
                                    </select><br>
                                    <input type="hidden" ng-model="actividades[dia][hora].idUsuario" value="<%
                                      
                                        out.write(tex);
                                    %>"/>
                                    <button ng-click="modificaActividad(dia, hora)">Guardar cambios</button>
                                    <button ng-click="eliminarActividad(dia,hora)">Borrar Actividad</button>
                                </div>
                                
                            </section>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="formaActividad" align="center">
            <input type="text" placeholder="Nombre" ng-model="formaActividad.nombre"></input><br>
    	    <input type="text" placeholder="DescripciÃ³n" ng-model="formaActividad.descripcion"></input><br>    
            <label>Avance: </label>
            <select ng-model="formaActividad.avance">
    	        <option value="No iniciada">No iniciada</option>
        		<option value="En curso">En curso</option>
        		<option value="Terminada">Terminada</option>
            </select>
            
            <br>
            Estado: <select ng-model="formaActividad.idUsuario">
    	        <option value="<%
                                        out.write(tex);
                                    %>">No iniciada</option>
            </select><br>
            <label>Dia de inicio: {{ formaActividad.diaInicio }}</label><br>
            <label>Hora de inicio: {{ formaActividad.horaInicio | date:"HH:mm" }}</label><br>

            <label>Dia de finalización: {{ formaActividad.diaFin }}</label><br>

            <label>Hora de finalización: {{ formaActividad.horaFin | date:"HH:mm" }}</label><br>
            
	    <button ng-click="nuevaActividad()">Guardar</button>
	</div>
    </body>
</html>
