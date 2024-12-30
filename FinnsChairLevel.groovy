import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Cylinder

// code here
CSG hole = new Cylinder(2*25.4/2.0,(1.0/4.0)*25.4, (int)64).toCSG()
CSG cutter = new Cube(3*25.4).toCSG()
					.toZMin()
					.toXMin()
					.roty(6)
					.movez(1.25*25.5);


CSG core = new Cylinder(2.5*25.4/2.0,1.6*25.4, (int)64).toCSG()

CSG wedge = new Cylinder(4,2,(int)3).toCSG()
				.toZMax()
				.toXMax()
				.toXMin(core)
//				.movex(2)
//				.movez(core.getMaxZ())
				.movex(core.getMaxX())
				.roty(-6)
				.rotx(180)

core = core.difference(hole)
				.toXMin()
				.difference(cutter)
				.roty(-6)
				.rotx(180)
				
wedge = wedge.movez(-core.getMinZ())
core = core.toZMin()
				
stand = core.union(wedge)
				
println "Setting CSG attributes"
stand = stand.setColor(javafx.scene.paint.Color.DARKTURQUOISE)
//			.setIsWireFrame(true)
			.setName("stand")
			.addAssemblyStep(0, new Transform())
			.setManufacturing({ toMfg ->
				return toMfg
						//.rotx(180)// fix the orientation
						//.toZMin()//move it down to the flat surface
			})
				

return stand
