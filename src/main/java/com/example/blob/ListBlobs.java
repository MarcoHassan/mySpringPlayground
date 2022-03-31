package com.example.blob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ListBlobs {
	
	Dotenv dotenv = Dotenv.configure()
			  		.directory("c:/Dev/Workspace/mySpringBootApp/initial")
			  		.load();

	@Operation(summary = "This is a list of available Blobs")
	@GetMapping("/myBlobs")
	public void listAvailableBlobs() throws IOException {
		// define here the logic to interact with blob storage.
		
        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();

        System.out.println(defaultCredential);
        
		// Create a BlobServiceClient object which will be used to create a container client
        BlobContainerClient blobClient = new BlobContainerClientBuilder()
                .credential(defaultCredential)
                .endpoint(dotenv.get("BlobContainerEndpoint"))
                .buildClient();

        // List the blob(s) in the container.
        // ok good working now. note that feeds is a container and it lists all blobs into it.
        for (BlobItem blobItem : blobClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }
	}
	
	
	// So note that here the focus is different. 
	// In the above you pass as connection string the endpoint of the container
	// with the blob target. Here you simply specify the endpoint of the container.
	// from there you expand.
	@Operation(summary = "This creates a quickstart* file into the helloworld blob")
	@GetMapping("/createBlob")
	public String createBlob() throws IOException {
		
		// define here the logic to interact with blob storage.
		// This when you work in the prod env. There everything is well integrated.
		// otherwise use the connection String in your space.
		// Ask Valerio at some point the ADF set up. 
		
        // DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder()
        // 											   .build();		
		
        String connectStr = dotenv.get("AZURE_STORAGE_CONNECTION_STRING");

        
		// Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServClient = new BlobServiceClientBuilder()
                // .credential(defaultCredential) // for prod env
        		.connectionString(connectStr) // for sandbox env
                .endpoint(dotenv.get("pocETLblob"))
                .buildClient();

        // Create a local file in the ./data/ directory 
        // for uploading and downloading
        String localPath = "./data/";
        String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
        File localFile = new File(localPath + fileName);
        
        blobServClient.listBlobContainers().forEach(container -> System.out.printf("Name: %s%n", container.getName()));
        
        // Note if you create it once it is fine. You would get an error when calling it.
        // BlobContainerClient containerClient = blobServClient.createBlobContainer("helloworld");
        
        // Use instead the following:
        BlobContainerClient containerClient = blobServClient.getBlobContainerClient("helloworld");

        // Write text to the file
        FileWriter writer = new FileWriter(localPath + fileName, true);
        writer.write("Hello, World!");
        writer.close();

        // Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Upload the blob
        blobClient.uploadFromFile(localPath + fileName);

        return "Blob Updated";
	}
	
}
