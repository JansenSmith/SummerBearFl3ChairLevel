import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Cylinder

// code here
CSG hole = new Cylinder(2*25.4/2.0,(1.0/4.0)*25.4).toCSG()
CSG cutter = new Cube(3*25.4).toCSG()
.toZMin()
.toXMin()
.roty(6)
.movez(1.25*25.5);


CSG core = new Cylinder(2.5*25.4/2.0,1.6*25.4).toCSG()
				.difference(hole)
				.toXMin()
				.difference(cutter)
				.roty(-6)
				.rotx(180)
				.toZMin()
return core
