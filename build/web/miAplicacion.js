var miAplicacion = angular.module("miAplicacion", []);

miAplicacion.controller('Principal',[
    '$scope', '$http', '$httpParamSerializerJQLike', function($scope,$http,$httpParamSerializerJQLike){
    	$scope.indice=1
        $scope.title='hola'
        $scope.indice=1;
        $scope.dias=[
            'lunes',
            'martes',
            'miercoles',
            'jueves',
            'viernes'
        ]
        $scope.generaHoras=function(){
            var hora, arreglo=[];
            for(hora=6;hora<24;hora++){
                var fecha=new Date();
                fecha.setHours(hora);
                fecha.setMinutes(0);
                arreglo.push(fecha);
                var fecha2=new Date();
                fecha2.setHours(hora);
                fecha2.setMinutes(30);
                arreglo.push(fecha2);
            }
            return arreglo;
        }
        $scope.horas=$scope.generaHoras();
        $scope.generaActividades=function(){
            var actividades={};
            var todasLasHoras=function(){
                var todas={};
                $scope.horas.forEach(function(hora){
                    var objetoVacio={};
                    todas[hora]=objetoVacio; 
                });
                return todas;
            }
            $scope.dias.forEach(function(dia){
                actividades[dia]=todasLasHoras();
            });
            return actividades;
        }
        $scope.actividades=$scope.generaActividades();
        $scope.formaActividad={
            idUsuario:'',
            id:'',
            nombre:'',
            descripcion:'',
            avance:'',
            diaInicio:'',
            horaInicio:'',
            diaFin:'',
            horaFin:'',
        }
        $scope.inicio=function(dia,hora){
            $scope.formaActividad.diaInicio=dia;
            $scope.formaActividad.horaInicio=hora;
        }
        $scope.fin=function(dia,hora){
            $scope.formaActividad.diaFin=dia;
            $scope.formaActividad.horaFin=hora;
        }
        $scope.actividadesGuardadas=[]
        $scope.copiaActividad=function(actividad){
            var copiaForma={
                idUsuario:actividad.idUsuario,
            	id:actividad.id,
                nombre:actividad.nombre,
                descripcion:actividad.descripcion,
                avance:actividad.avance,
                diaInicio:actividad.diaInicio,
                horaInicio:actividad.horaInicio,
                diaFin:actividad.diaFin,
                horaFin:actividad.horaFin
            }
            return copiaForma;
        }
        $scope.llenadoDeDia=function(dia, horaInicio, horaFin, actividad){
            for(var i=horaInicio; i<horaFin; i++)
                $scope.actividades[$scope.dias[dia]][$scope.horas[i]]=actividad; 
        }
        $scope.nuevaActividad=function(){
            
            var copiaForma=$scope.copiaActividad($scope.formaActividad);
            var diaActual=$scope.dias.indexOf(copiaForma.diaInicio);
            var horaInicial=$scope.horas.indexOf(copiaForma.horaInicio);
            var horaFinal=$scope.horas.indexOf(copiaForma.horaFin);
            
            console.log(copiaForma.id);
            
            copiaForma.id=$scope.indice;
            $scope.indice++;

            $http({
                method: 'POST',
                url: 'nuevaActividad',
                data: $httpParamSerializerJQLike($scope.formaActividad),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })

            $scope.generaActividades(copiaForma, copiaForma,diaActual,horaInicial,horaFinal);
            $scope.actividadesGuardadas.push(copiaForma);
        }
        $scope.eliminarActividad=function(dia, hora){
            var actividadReferencia=$scope.actividades[dia][hora];
            var objetoVacio={};
            var diaActual=$scope.dias.indexOf(actividadReferencia.diaInicio);
            var horaInicial=$scope.horas.indexOf(actividadReferencia.horaInicio);
            var horaFinal=$scope.horas.indexOf(actividadReferencia.horaFin);

            $http({
                method: 'POST',
                url: 'borrarActividad',
                data: $httpParamSerializerJQLike(actividadReferencia),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })

            $scope.generaActividades(actividadReferencia, objetoVacio, diaActual, horaInicial, horaFinal); 
        }
        $scope.generaActividades=function(actividadReferencia, actividadAGuardar, diaActual, horaInicial, horaFinal){
            if(actividadReferencia.diaInicio===actividadReferencia.diaFin){ 
                $scope.llenadoDeDia(diaActual, horaInicial, horaFinal, actividadAGuardar);
            }
            else{
                $scope.llenadoDeDia(diaActual, horaInicial, $scope.horas.length, actividadAGuardar);
                diaActual++;
                while($scope.dias[diaActual]!==actividadReferencia.diaFin&&diaActual<$scope.dias.length){
                    $scope.llenadoDeDia(diaActual, 0, $scope.horas.length, actividadAGuardar);
                    diaActual++;
                }
                if($scope.dias[diaActual]===actividadReferencia.diaFin){
                    console.log(diaActual);
                    $scope.llenadoDeDia(diaActual, 0, horaFinal, actividadAGuardar);
                }else{
                    console.log("Vale gorro");
                }
            }
        }
        $scope.modificaActividad=function(dia,hora){
        	$http({
                method: 'POST',
                url: 'modificaActividad',
                data: $httpParamSerializerJQLike($scope.actividades[dia][hora]),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })
        }
    }
]);
