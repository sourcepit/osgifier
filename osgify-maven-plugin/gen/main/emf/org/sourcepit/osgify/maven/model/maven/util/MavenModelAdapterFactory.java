/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.maven.model.maven.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.osgify.maven.model.maven.Classified;
import org.sourcepit.osgify.maven.model.maven.Identifiable;
import org.sourcepit.osgify.maven.model.maven.MavenArtifact;
import org.sourcepit.osgify.maven.model.maven.MavenDependency;
import org.sourcepit.osgify.maven.model.maven.MavenModelPackage;
import org.sourcepit.osgify.maven.model.maven.MavenProject;
import org.sourcepit.osgify.maven.model.maven.VersionedIdentifiable;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.sourcepit.osgify.maven.model.maven.MavenModelPackage
 * @generated
 */
public class MavenModelAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static MavenModelPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MavenModelAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = MavenModelPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object
    * of the model.
    * <!-- end-user-doc -->
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected MavenModelSwitch<Adapter> modelSwitch = new MavenModelSwitch<Adapter>()
      {
         @Override
         public Adapter caseMavenArtifact(MavenArtifact object)
         {
            return createMavenArtifactAdapter();
         }
         @Override
         public Adapter caseMavenDependency(MavenDependency object)
         {
            return createMavenDependencyAdapter();
         }
         @Override
         public Adapter caseIdentifiable(Identifiable object)
         {
            return createIdentifiableAdapter();
         }
         @Override
         public Adapter caseVersionedIdentifiable(VersionedIdentifiable object)
         {
            return createVersionedIdentifiableAdapter();
         }
         @Override
         public Adapter caseClassified(Classified object)
         {
            return createClassifiedAdapter();
         }
         @Override
         public Adapter caseMavenProject(MavenProject object)
         {
            return createMavenProjectAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object)
         {
            return createEObjectAdapter();
         }
      };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject)target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.maven.model.maven.MavenArtifact <em>Maven Artifact</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.sourcepit.osgify.maven.model.maven.MavenArtifact
    * @generated
    */
   public Adapter createMavenArtifactAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.maven.model.maven.MavenDependency <em>Maven Dependency</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.sourcepit.osgify.maven.model.maven.MavenDependency
    * @generated
    */
   public Adapter createMavenDependencyAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.maven.model.maven.Identifiable <em>Identifiable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.sourcepit.osgify.maven.model.maven.Identifiable
    * @generated
    */
   public Adapter createIdentifiableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.maven.model.maven.VersionedIdentifiable <em>Versioned Identifiable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.sourcepit.osgify.maven.model.maven.VersionedIdentifiable
    * @generated
    */
   public Adapter createVersionedIdentifiableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.maven.model.maven.Classified <em>Classified</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.sourcepit.osgify.maven.model.maven.Classified
    * @generated
    */
   public Adapter createClassifiedAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.maven.model.maven.MavenProject <em>Maven Project</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.sourcepit.osgify.maven.model.maven.MavenProject
    * @generated
    */
   public Adapter createMavenProjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} // MavenModelAdapterFactory
